import unittest
import dictutils


class MyTestCase(unittest.TestCase):

    def test_core(self):
        di = {'un': 'one', 'deux': 'deux', 'trois': 'three'}
        dictutils.remove_equals(di)
        expected = {'un': 'one', 'trois': 'three'}
        self.assertEqual(expected, di)

    def test_all(self):
        di = {'A': 'A', 'B': 'B', 'C': 'C', 'D': 'D'}
        dictutils.remove_equals(di)
        expected = {}
        self.assertEqual(expected, di)

    def test_comparison(self):
        palindrome = 'kayak'
        k = palindrome
        v = palindrome[::-1]
        if k is v:
            self.skipTest('cannot make distinct strings with same content')
        di = {k: v}
        dictutils.remove_equals(di)
        expected = {}
        self.assertEqual(expected, di)


if __name__ == '__main__':
    unittest.main()
