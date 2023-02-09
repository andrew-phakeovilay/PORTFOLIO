import datetime
import json
import os
import yaml
from time import sleep
import paho.mqtt.client as mqtt

with open("config_mqtt.yaml", "r") as stream:
    try:
        config = yaml.safe_load(stream)
    except yaml.YAMLError as exc:
        print(exc)

for e in config["object"]:
    try:
        os.remove(config["name"] + "_" + e + "_donnees.txt")
    except OSError:
        pass
    try:
        os.remove(config["name"] + "_alert_" + e + "_donnees.txt")
    except OSError:
        pass


def get_data(mqtt, obj, msg):
    jsonMsg = json.loads(msg.payload)
    now = datetime.datetime.now()
    date = now.strftime("%Y-%b-%d %H-%M-%S")
    for e in config["object"]:
        alert = False
        if config["object"][e]:
            if e == "activity":
                if config["seuils"][e] is not None:
                    if config["seuils"][e] < jsonMsg["object"][e] and (now.hour > 18 or now.hour < 6):
                        alert = True
            elif e == "co2" or e == "humidity" or e == "temperature":
                if config["seuils"][e][0] is not None:
                    if config["seuils"][e][1] is not None and config["seuils"][e][1] < jsonMsg["object"][e] or \
                            config["seuils"][e][0] > jsonMsg["object"][e]:
                        alert = True
            else:
                if config["seuils"][e] is not None:
                    if config["seuils"][e] < jsonMsg["object"][e]:
                        alert = True
            if alert:
                with open(config["name"] + "_alert_" + e + "_donnees.txt", "a", encoding="utf-8") as file:
                    file.write(str(jsonMsg["object"][e]) + ":" + date + "\n")

            with open(config["name"] + "_" + e + "_donnees.txt", "a", encoding="utf-8") as file:
                file.write(str(jsonMsg["object"][e]) + "\n")
    sleep(config["frequency"])


print("Connexion aux locaux de Blue Gym...")

client = mqtt.Client()
client.connect(config["servers"], config["port"], 600)

client.subscribe("application/1/device/+/event/up")

client.on_message = get_data

client.loop_forever()
