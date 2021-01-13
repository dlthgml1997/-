# https://programmers.co.kr/learn/courses/30/lessons/68644
from itertools import combinations

def solution(numbers):
    answer = []
    for a,b in combinations(numbers, 2):
        answer.append(a+b)
    answer = set(answer)
    answer = list(answer)
    answer.sort()
    return answer