# -*- coding: utf-8 -*-
"""
In this file your task is to write the solver function!

"""
from collections import defaultdict

from RangeConstants import TConstants, WConstants, FConstants


def triangle_check(elem, possibilities):
    results = []
    for possib in possibilities:
        m = (possib[0] + possib[1]) // 2
        if possib[0] >= elem or elem >= possib[1]:
            results.append(0)
        elif elem > m:
            results.append((possib[1] - elem) / (possib[1] - m))
        else:
            results.append((elem - possib[0]) / (m - possib[0]))
    return results


def solver(t, w):
    """
    Parameters
    ----------
    t : TYPE: float
        DESCRIPTION: the angle theta
    w : TYPE: float
        DESCRIPTION: the angular speed omega

    Returns
    -------
    F : TYPE: float
        DESCRIPTION: the force that must be applied to the cart
    or
    
    None :if we have a division by zero

    """
    T_List = triangle_check(t, TConstants.T_RANGE)
    W_List = triangle_check(w, WConstants.W_RANGE)
    tabel = defaultdict(list)
    tabel["NVVB"].append(min(T_List[0], W_List[0]))
    tabel["NVVB"].append(min(T_List[0], W_List[1]))
    tabel["NVVB"].append(min(T_List[1], W_List[0]))

    tabel["NVB"].append(min(T_List[2], W_List[0]))
    tabel["NVB"].append(min(T_List[1], W_List[1]))
    tabel["NVB"].append(min(T_List[0], W_List[2]))

    tabel["NB"].append(min(T_List[3], W_List[0]))
    tabel["NB"].append(min(T_List[2], W_List[1]))
    tabel["NB"].append(min(T_List[1], W_List[2]))
    tabel["NB"].append(min(T_List[0], W_List[3]))

    tabel["N"].append(min(T_List[4], W_List[0]))
    tabel["N"].append(min(T_List[3], W_List[1]))
    tabel["N"].append(min(T_List[2], W_List[2]))
    tabel["N"].append(min(T_List[1], W_List[3]))
    tabel["N"].append(min(T_List[0], W_List[4]))

    tabel["Z"].append(min(T_List[1], W_List[4]))
    tabel["Z"].append(min(T_List[3], W_List[2]))
    tabel["Z"].append(min(T_List[5], W_List[0]))
    tabel["Z"].append(min(T_List[2], W_List[3]))
    tabel["Z"].append(min(T_List[4], W_List[1]))

    tabel["PVVB"].append(min(T_List[5], W_List[4]))
    tabel["PVVB"].append(min(T_List[6], W_List[3]))
    tabel["PVVB"].append(min(T_List[6], W_List[4]))

    tabel["PVB"].append(min(T_List[4], W_List[4]))
    tabel["PVB"].append(min(T_List[5], W_List[3]))
    tabel["PVB"].append(min(T_List[6], W_List[2]))

    tabel["PB"].append(min(T_List[3], W_List[4]))
    tabel["PB"].append(min(T_List[4], W_List[3]))
    tabel["PB"].append(min(T_List[5], W_List[2]))
    tabel["PB"].append(min(T_List[6], W_List[1]))

    tabel["P"].append(min(T_List[2], W_List[4]))
    tabel["P"].append(min(T_List[3], W_List[3]))
    tabel["P"].append(min(T_List[4], W_List[2]))
    tabel["P"].append(min(T_List[5], W_List[1]))
    tabel["P"].append(min(T_List[6], W_List[0]))
    print(tabel)
    index = 0
    sir = FConstants.F_RANGE
    suma = 0
    imp = 0
    for e in tabel.keys():
        suma += sir[index] * max(tabel[e])
        index += 1
        imp += max(tabel[e])
    if imp != 0:
        res = suma // imp
        print(res)
        return res
    return None
