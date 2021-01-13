# https://programmers.co.kr/learn/courses/30/lessons/68935

def solution(n):
    answer = 0
    ternary = []
    while n > 0:
        ternary.append(n % 3)
        n //= 3

    temp = ''.join(map(str, ternary))
    return int(temp, 3)


# def solution(n):
#     answer = 0
#     ternary = []
#     while n > 0:
#         ternary.append(n % 3)
#         n //= 3
#
#     length = len(ternary) - 1
#     for i in ternary:
#         if i != 0:
#             num = 1
#             for _ in range(length):
#                 num *= 3
#             answer += num * i
#         length -= 1
#
#     return answer