def insert(lista, retList, elem, pos, i):
    if len(lista) == i:
        print(retList)
        return retList
    if pos == 0:
        retList.append(elem)
        pos = -1
        insert(lista, retList, elem, pos, i)
    else:
        retList.append(lista[i])
        pos -= 1
        i += 1
        insert(lista, retList, elem, pos, i)


def add_to_list(lista, i, elem):
    if(i == len(lista)):
        print(lista)
        return lista
    else:
        if i % 2 == 0:
            lista.insert(i,elem)
            add_to_list(lista, i + 1, elem)
        else:
            add_to_list(lista, i + 1, elem)

lista = [1,2,3,4,5]
insert(lista, [], 9, 2, 0)
print(add_to_list(lista, 0, 33))