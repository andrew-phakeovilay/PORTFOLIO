from datetime import datetime
import unittest

from data import RecordCollection


class MyTestCase(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        """
        Executed once for all the tests of this class.
        """
        cls.collection = RecordCollection.from_month(10, 2022)

    def test_stations(self):
        stations = self.collection.stations
        self.assertEqual(61, len(stations))

    def test_dates(self):
        dates = self.collection.dates
        self.assertEqual(31 * 8, len(dates))

    def test_get_record_temperature(self):
        date = datetime(2022, 10, 5, 12)
        station = '07630'
        record = self.collection.get_record(date, station)
        temperature = record.temperature
        self.assertAlmostEqual(23.7, temperature)

    def test_get_records_by_date(self):
        date = datetime(2022, 10, 5, 12)
        records = self.collection.get_records_by_date(date)
        self.assertEqual(61, len(records))

    def test_len(self):
        n = len(self.collection)
        self.assertEqual(14925, n)
        # could be 61 * 31 * 8 = 15128 but some records are missing

    def test_date(self):
        expected = datetime(2022, 10, 1, 0)
        date = self.collection[0].date
        self.assertEqual(expected, date)

    def test_station(self):
        expected = '07005'
        station = self.collection[0].station
        self.assertEqual(expected, station)

    def test_get_pressure(self):
        expected = '99450'
        pressure = self.collection[0]['pres']
        self.assertEqual(expected, pressure)


if __name__ == '__main__':
    unittest.main()
