import unittest
import dictutils


class MyTestCase(unittest.TestCase):

    def test_core(self):
        li = ['A', 'Z', 'A', 'B', 'A', 'Z']
        di = dictutils.frequency(li)
        expected = {'A': 3, 'B': 1, 'Z': 2}
        self.assertEqual(expected, di)


if __name__ == '__main__':
    unittest.main()
