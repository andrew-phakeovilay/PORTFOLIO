import unittest
import dictutils


class MyTestCase(unittest.TestCase):

    def test_core(self):
        di = {'A': 1, 'B': 2, 'C': 2}
        res = dictutils.invert(di)
        res_no_order = {k: set(v) for k, v in res.items()}
        expected = {1: {'A'}, 2: {'B', 'C'}}
        self.assertEqual(expected, res_no_order)


if __name__ == '__main__':
    unittest.main()
