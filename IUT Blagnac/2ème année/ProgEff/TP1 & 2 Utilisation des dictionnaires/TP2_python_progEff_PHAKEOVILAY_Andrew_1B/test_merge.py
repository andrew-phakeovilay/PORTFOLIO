import unittest
import dictutils

class MyTestCase(unittest.TestCase):
    
    def test_disjoint(self):
        di1 = {'A': 1, 'B': 2} 
        di2 = {'C': 2}
        res = dictutils.merge(di1, di2)
        expected = {'A': 1, 'B': 2, 'C': 2}
        self.assertEqual(expected, res)
        
    def test_max(self):
        di1 = {'A': 1, 'B': 2}
        di2 = {'A': 3, 'C': 2}
        res = dictutils.merge(di1, di2, max)
        expected = {'A': 3, 'B': 2, 'C': 2}
        self.assertEqual(expected, res)
        
    def test_add(self):
        di1 = {'A': 1, 'B': 2}
        di2 = {'A': 3, 'C': 2}
        res = dictutils.merge(di1, di2, lambda x, y: x + y)
        expected = {'A': 4, 'B': 2, 'C': 2}
        self.assertEqual(expected, res)
        
    def test_conflict(self):
        di1 = {'A': 1, 'B': 2}
        di2 = {'A': 3, 'C': 2}
        try:
            res = dictutils.merge(di1, di2)
        except ValueError:
            return  # expected error
        self.fail("Expecting ValueError, got: " + str(di))
        
    def test_no_side_effect(self):
        di1 = {'A': 1, 'B': 2}
        di2 = {'A': 3, 'C': 2}
        res = dictutils.merge(di1, di2, lambda x, y: x + y)
        expected1 = {'A': 1, 'B': 2}
        expected2 = {'A': 3, 'C': 2}
        self.assertEqual(expected1, di1)
        self.assertEqual(expected2, di2)


if __name__ == '__main__':
    unittest.main()
