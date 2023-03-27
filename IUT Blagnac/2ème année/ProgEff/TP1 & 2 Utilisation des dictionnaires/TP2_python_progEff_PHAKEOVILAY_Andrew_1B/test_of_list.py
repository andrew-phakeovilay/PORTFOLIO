import unittest
import dictutils


class MyTestCase(unittest.TestCase):

    def test_core(self):
        li = ["lol:mdr", "bye:ciao"]
        di = dictutils.of_pair_list(li)
        expected = {'lol': 'mdr', 'bye': 'ciao'}
        self.assertEqual(expected, di)

    def test_same_values(self):
        li = ["bonjour:hello", "salut:hello", "coucou:hello"]
        di = dictutils.of_pair_list(li)
        expected = {'bonjour': 'hello', 'salut': 'hello', 'coucou': 'hello'}
        self.assertEqual(expected, di)

    def test_too_few_columns(self):
        li = ["lol_mdr"]
        try:
            di = dictutils.of_pair_list(li)
        except ValueError:
            return  # expected error
        self.fail("Expecting ValueError, got: " + str(di))

    def test_too_much_columns(self):
        li = ["lol:mdr:kikou"]
        try:
            di = dictutils.of_pair_list(li)
        except ValueError:
            return  # expected error
        self.fail("Expecting ValueError, got: " + str(di))


if __name__ == '__main__':
    unittest.main()
