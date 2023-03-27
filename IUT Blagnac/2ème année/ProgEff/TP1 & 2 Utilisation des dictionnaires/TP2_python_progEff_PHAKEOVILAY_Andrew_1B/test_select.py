import unittest
import dictutils


class MyTestCase(unittest.TestCase):

    def test_core(self):
        di = {'UA': 'not funny', 'AA': 'funny', 'IUT': 'funny'}
        li = dictutils.select_by_value(di, 'funny')
        expected = ['AA', 'IUT']
        self.assertEqual(set(expected), set(li))

    def test_single(self):
        di = {'UA': 'not funny', 'AA': 'funny', 'IUT': 'funny'}
        li = dictutils.select_by_value(di, 'not funny')
        expected = ['UA']
        self.assertEqual(expected, li)

    def test_none(self):
        di = {'UA': 'not funny', 'AA': 'funny', 'IUT': 'funny'}
        li = dictutils.select_by_value(di, 'super funny')
        expected = []
        self.assertEqual(expected, li)

    def test_comparison(self):
        palindrome = 'kayak'
        word = palindrome
        word_also = palindrome[::-1]
        if word is word_also:
            self.skipTest('cannot make distinct strings with same content')
        di = {'UA': word, 'AA': 'funny', 'IUT': 'funny'}
        li = dictutils.select_by_value(di, word_also)
        expected = ['UA']
        self.assertEqual(expected, li)


if __name__ == '__main__':
    unittest.main()
