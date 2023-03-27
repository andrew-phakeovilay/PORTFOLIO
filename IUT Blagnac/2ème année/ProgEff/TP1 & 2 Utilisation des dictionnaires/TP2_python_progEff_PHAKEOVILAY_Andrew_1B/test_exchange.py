import unittest
import dictutils


class MyTestCase(unittest.TestCase):

    def test_str_int(self):
        di = {'un': 1, 'deux': 2, 'trois': 3}
        dictutils.exchange(di, 'un', 'deux')
        expected = {'un': 2, 'deux': 1, 'trois': 3}
        self.assertEqual(expected, di)

    def test_int_str(self):
        di = {1: 'un', 2: 'deux', 3: 'trois'}
        dictutils.exchange(di, 2, 3)
        expected = {1: 'un', 3: 'deux', 2: 'trois'}
        self.assertEqual(expected, di)


if __name__ == '__main__':
    unittest.main()
