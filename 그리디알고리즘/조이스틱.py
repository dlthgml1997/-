# https://programmers.co.kr/learn/courses/30/lessons/42860
def solution(name):
    name_c = []
    answer = 0

    for c in name:
        name_c.append(min(ord('Z') - ord(c) + 1, ord(c) - ord('A')))

    if 'A' not in name:
        answer = sum(name_c) + len(name) - 1
        return answer

    cursor = 0

    while 1:
        left = 1
        right = 1
        answer += name_c[cursor]
        name_c[cursor] = 0
        if sum(name_c) == 0:
            return answer
        while name_c[cursor - left] == 0:
            left += 1
        while name_c[cursor + right] == 0:
            right += 1

        if left < right:
            cursor -= left
            answer += left
        else:
            cursor += right
            answer += right