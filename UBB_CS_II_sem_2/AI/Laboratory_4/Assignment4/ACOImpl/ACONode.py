class ACONode:
    def __init__(self, x, y):
        self.__x = x
        self.__y = y

    def get_x(self):
        return self.__x

    def get_y(self):
        return self.__y

    def set_x(self, value):
        self.__x = value

    def set_y(self, value):
        self.__y = value

    def __str__(self):
        stg = "N(" + str(self.__x) + ", " + str(self.__y) + ")"
        return stg
