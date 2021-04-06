import itertools


def compute_max_value(arr):
    lgt = len(arr)
    odd_isum = 0
    even_isum = 0
    for i in range(lgt):
        if i % 2 == 0:
            even_isum += arr[i]
        else:
            odd_isum += arr[i]
    res = even_isum - odd_isum
    res = res * res
    return res


def maxSubarrayValue(arr):
    list = allSubArrays(arr)
    maxi = -99999
    for i in list:
        pot_maxi = compute_max_value(i)
        print(str(i) + " => " + str(pot_maxi))
        if pot_maxi >= maxi:
            maxi = pot_maxi
    return maxi


def allSubArrays(L, L2=None):
    if L2 == None:
        L2 = L[:-1]
    if L == []:
        if L2 == []:
            return []
        return allSubArrays(L2, L2[:-1])
    return [L] + allSubArrays(L[1:], L2)


if __name__ == "__main__":
    arr = [6,
           3,
           -1,
           -1,
           -1,
           5,
           1]
    print(str(maxSubarrayValue(arr)))
