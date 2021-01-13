# https://www.acmicpc.net/problem/1065
n = int(input())

def check(nums):
    div = nums[1] - nums[0]
    for i in range(len(nums)-1):
        if div != nums[i+1] - nums[i]:
            return 0
    return 1



def solution():
    answer = 99
    if n < 100:
        print(n)
        return
    else:
        for i in range(100, n+1):
            nums = list(map(int, str(i).strip()))
            answer += check(nums)

    print(answer)

solution()