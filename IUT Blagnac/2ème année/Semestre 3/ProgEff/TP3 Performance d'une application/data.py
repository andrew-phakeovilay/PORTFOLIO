"""
Module for loading meteo data
"""

import csv
import gzip
import os
import urllib.request
from datetime import datetime
from typing import List, OrderedDict, Optional, Set, Iterator

csv.register_dialect('synop', 'excel', delimiter=';')

file_pattern = 'synop_files/synop.{:04d}{:02d}.csv.gz'


class Record(object):
    """
    A record represents one line of a synop file, with station number + date
    and all the meteo information.
    """

    def __init__(self, row: OrderedDict):
        """
        :param row: one row of the CSV file
        """
        self.row = row

    def __repr__(self):
        return repr(self.row)

    def __getitem__(self, item) -> str:
        """
        Allows to write r['t'] to get the value of the `t` column of the row `r`.
        """
        return self.row[item]

    @property
    def date(self) -> datetime:
        return datetime.strptime(self['date'], "%Y%m%d%H%M%S")

    @property
    def station(self) -> str:
        return self['numer_sta']

    @property
    def temperature(self) -> Optional[float]:
        t = self['t']
        if t == 'mq':
            return None
        else:
            return float(t) - 273.15


class RecordCollection(object):
    """
    Collection of records.
    """

    def __init__(self, records: List[Record]):
        self.records = records
        self.dicDate = self.constructDicDate()

    def __repr__(self):
        return f'<RecordCollection of {len(self)} records>'

    def __getitem__(self, item):
        """
        Allows to access the record collection as a list of records
        """
        return self.records[item]

    def __len__(self) -> int:
        return len(self.records)

    def __iter__(self) -> Iterator[Record]:
        """
        Allows to iterate on the record collection
        """
        return iter(self.records)

    @property
    def stations(self) -> Set[str]:
        return set(r.station for r in self)

    @property
    def dates(self) -> List[datetime]:
        return sorted(set(r.date for r in self))

    def get_record(self, date: datetime, station: str) -> Optional[Record]:
        for r in self.dicDate[date]:
            if r.station == station:
                return r
        return None

    def get_records_by_date(self, date: datetime) -> List[Record]:
        return self.dicDate[date]

    @classmethod
    def from_month(cls, month: int, year: int) -> 'RecordCollection':
        file = file_pattern.format(year, month)
        if not os.path.exists(file):
            url = 'https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/Archive/' + file.split('/')[1]
            print('Téléchargement', url)
            urllib.request.urlretrieve(url, file)
            if not os.path.exists(file):
                raise Exception('Problème survenu')
        return cls.from_file(file)

    @classmethod
    def from_file(cls, file) -> 'RecordCollection':
        csv_file = gzip.open(file, mode='rt', newline='')
        csv_data = csv.DictReader(csv_file, dialect='synop')
        records = [Record(r) for r in csv_data]
        return RecordCollection(records)

    def constructDicDate(self):
        dicContructed = {}
        for attribute in self:
            if attribute.date in dicContructed.keys():
                dicContructed[attribute.date].append(attribute)
            else:
                dicContructed[attribute.date] = [attribute]
        return dicContructed


def main():
    collection = RecordCollection.from_month(10, 2022)
    print("Les deux premiers releves :")
    print(*collection[:2], sep=os.linesep)
    print("L'ensemble des stations :")
    print(collection.stations)
    print("L'ensemble trie des dates :")
    print(collection.dates)


if __name__ == '__main__':
    main()
