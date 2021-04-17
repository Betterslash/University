def isSorted(number):
    if number > 9:
        lista = [int(x) for x in str(number)]
        lista.sort(reverse=True)
        check_list = [int(x) for x in str(number)]
        for i in range(len(check_list)):
            if check_list[i] != lista[i]:
                return False
        return True
    else:
        return True


def permutation(lista, l, r, result):
    if l == r:
        result.append(lista)
    else:
        for i in range(l, r + 1):
            lista[l], lista[i] = lista[i], lista[l]
            permutation(lista, l, r, result)
            lista[l], lista[i] = lista[i], lista[l]


def solver(number):
    if isSorted(number):
        print("not possible")
    else:
        lista = [int(x) for x in str(number)]
        lgt = len(lista)
        res = []
        permutation(lista, 0, lgt - 1, res)
        res.sort()
        return res[1]

if __name__ == "__main__":
    print(solver(218765))

