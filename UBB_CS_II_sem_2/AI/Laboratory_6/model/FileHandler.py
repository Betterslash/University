from model.ItemEntry import ItemEntry


class FileHandler:
    def __init__(self, file_path):
        self.__file_path = file_path

    def get_file_path(self):
        return self.__file_path

    def __str__(self):
        return str(self.__file_path)

    def read_file(self) -> list[ItemEntry]:
        file = open(self.__file_path)
        lines = file.read().splitlines()
        file.close()

        file_items = []
        for i in range(1, len(lines)):
            line = lines[i].split(",")
            type_var = line[0]
            x_value = float(line[1])
            y_value = float(line[2])
            item_entry = ItemEntry(type_var, x_value, y_value)
            file_items.append(item_entry)

        # shuffle(file_items)
        return file_items




