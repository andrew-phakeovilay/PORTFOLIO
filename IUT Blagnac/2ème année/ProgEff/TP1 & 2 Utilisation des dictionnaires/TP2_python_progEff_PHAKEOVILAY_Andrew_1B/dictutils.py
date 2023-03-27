"""
Library for processing dictionaries.
"""
from typing import Dict, Callable, List


def exchange(di: Dict, k1, k2):
    """
    Exchange the values attached to two given keys.

    :param di: the dictionary where the exchange is performed
    :param k1: one key
    :param k2: another key
    """

    permut = di[k1]
    di[k1] = di[k2]
    di[k2] = permut



def of_pair_list(li: List[str]) -> Dict[str, str]:
    """
    Construct a dictionary from a list of strings representing the key-value pairs.
    Each string must be of the form "key:value" where key and value contains no ':'.

    :param li: a list of strings, each representing a pair
    :return: the corresponding dictionary.
    :raises ValueError: if any of the strings is not well-formed.
    """

    dict = {}
    for str in li:
        keyValue = str.split(":")
        if len(keyValue) != 2:
            raise ValueError
        dict[keyValue[0]] = keyValue[1]
    return dict



def select_by_value(di: Dict, va) -> List:
    """
    Return the list of keys that map to a given value.

    :param di: the dictionary where to search
    :param va: the value to search
    :return: the list of keys mapping to that value.
    """

    listKeys = []
    for key in di:
        if di[key] == va:
            listKeys.append(key)
    return listKeys


def remove_equals(di: Dict):
    """
    Remove the pairs where the key is equal to the value.

    :param di: the dictionary where to remove the pairs
    """

    pairRemove = []
    for key, value in di.items():
        if key == value:
            pairRemove.append(key)
    for key in pairRemove:
        di.pop(key)


def frequency(li: List) -> Dict:
    """
    Compute the number of time that each element appears in a list.
    The result is given as a dictionary binding elements to number of appearance.

    :param li: the list where to count
    :return: a dictionary associating to each element of the list the number of times it appears in the list.
    """

    dictOcc = {}
    for element in li:
        if element in dictOcc:
            dictOcc[element] += 1
        else:
            dictOcc[element] = 1
    return dictOcc


def invert(di: Dict) -> Dict:
    """
    Invert a dictionary by computing a new dictionary where the keys are the _old values and the values are
    lists of _old keys.

    :param di: the dictionary to revert
    :return: a new dictionary, inverse of the input one.
    """

    dictInverted = {}
    for key, value in di.items():
        if value in dictInverted:
            dictInverted[value].append(key)
        else:
            dictInverted[value] = []
            dictInverted[value].append(key)
    return dictInverted


def merge(d1: Dict, d2: Dict, combine: Callable = None) -> Dict:
    """
    Merge the input dictionaries together.
    If a key is bound in both dictionaries, the associated values are combined using the combination function.

    :param d1: the first dictionary
    :param d2: the second dictionary
    :param combine: the combination function (optional)
    :return: a new dictionary merge the input dictionaries.
    :raises ValueError: if a key is in both dictionaries and no combination function is provided.
    """

    dictMerged = {}
    for key, value in d1.items():
        dictMerged[key] = value
    for key, value in d2.items():
        if key in dictMerged:
            if combine is None:
                raise ValueError
            else:
                dictMerged[key] = combine(value, dictMerged[key])
        else:
            dictMerged[key] = value
    return dictMerged