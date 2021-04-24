from constants.Constants import Constants


class ItemEntry:
    def __init__(self, type_var, x, y):
        self.__type = type_var
        self.__x = x
        self.__y = y
        self.__color = self.__get_color()
    def get_x(self):
        return self.__x

    def get_y(self):
        return self.__y

    def get_type(self):
        return self.__type

    def __str__(self):
        return str(self.__type) + ", " + str(self.__x) + ", " + str(self.__y)

    def __iter__(self):
        for attr in self.__dict__.values():
            if not attr.startswith("__"):
                yield attr

    def __len__(self):
        return Constants.ITEM_FIELD_NUMBER

    def __getitem__(self, item):
        if item == 0:
            return self.__type
        elif item == 1:
            return self.__x
        elif item == 2:
            return self.__y

    def __get_color(self):
        if self.__type == "A":
            return Constants.COLORS[0]

        if self.__type == "B":
            return Constants.COLORS[1]

        if self.__type == "C":
            return Constants.COLORS[2]

        if self.__type == "D":
            return Constants.COLORS[3]

    def get_color(self):
        return self.__color

