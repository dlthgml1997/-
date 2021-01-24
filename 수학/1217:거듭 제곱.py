# https://swexpertacademy.com/main >

def multiple(answer, num, cnt, limit):
    if cnt == limit:
        return answer
    return multiple(answer * num, num, cnt + 1, limit)

for _ in range(10):
    t = input()
    a, b = map(int, input().split())
    print("#"+t+" "+str(multiple(a, a, 1, b)))