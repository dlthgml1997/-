# https://programmers.co.kr/learn/courses/30/lessons/42578?language=python3
from collections import Counter
from functools import reduce

def solution(clothes):
    count = Counter([name for category, name in clothes])
    return reduce(lambda x, y: x * (y + 1), count.values(), 1) - 1