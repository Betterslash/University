class Node:
    def __init__(self, X, Y, Value, g_function, h_function, f_function):
        self.value = Value
        self.x = X
        self.y = Y
        self.g = g_function
        self.f = f_function
        self.h = h_function

    def __str__(self):
        return str(self.value)
