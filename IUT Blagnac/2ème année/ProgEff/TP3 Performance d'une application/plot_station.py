import datetime

import matplotlib.pyplot as plt
import matplotlib
import snakeviz

from data import RecordCollection


def plot_station_temperatures(collection: RecordCollection, station: str):
    print(f'Affichage des temperatures de la station {station}')
    print(f'(1) collecte des donnees')
    x_labels = []
    y_values = []
    debut = datetime.datetime.now()
    for date in collection.dates:
        record = collection.get_record(date, station)
        if record is not None:
            x_labels.append(date)
            y_values.append(record.temperature)
    fin = datetime.datetime.now()
    duree = fin - debut
    print(duree, 's')
    print(f'(2) affichage de {len(y_values)} temperatures')
    plt.plot(x_labels, y_values)
    plt.show()


def demo(station: str):
    collection = RecordCollection.from_month(10, 2022)
    plot_station_temperatures(collection, station)


if __name__ == '__main__':
    demo('07630')
