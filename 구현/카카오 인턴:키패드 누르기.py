# https://programmers.co.kr/learn/courses/30/lessons/67256
phone = {1: (0, 0), 2: (1, 0), 3: (2, 0),
         4: (0, 1), 5: (1, 1), 6: (2, 1),
         7: (0, 2), 8: (1, 2), 9: (2, 2),
         -1: (0, 3), 0: (1, 3), -2: (2, 3)}


def get_distance(number, x, y):
    nx, ny = phone.get(number)
    return abs(nx - x) + abs(ny - y)


def solution(numbers, hand):
    answer = ''
    hand_l = -1
    hand_r = -2
    x = y = -1
    for n in numbers:
        if n in [1, 4, 7]:
            answer += 'L'
            hand_l = n
        elif n in [3, 6, 9]:
            answer += 'R'
            hand_r = n
        else:  # n 이 2번째 열의 숫자인 경우
            cnt = 0
            x, y = phone.get(hand_l)
            dis_l = get_distance(n, x, y)
            x, y = phone.get(hand_r)
            dis_r = get_distance(n, x, y)

            if dis_l == dis_r:
                if hand == "left":
                    answer += 'L'
                    hand_l = n
                else:
                    answer += 'R'
                    hand_r = n
            elif dis_l < dis_r:
                answer += 'L'
                hand_l = n
            elif dis_l > dis_r:
                answer += 'R'
                hand_r = n

    return answer
