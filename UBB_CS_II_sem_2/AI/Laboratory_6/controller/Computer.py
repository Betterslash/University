class Computer:
    def __init__(self, clusters):
        self.__items = clusters
        self.__values = {"accuracy": 0., "precision": 0, "rappel": 0, "score": 0}
        self.__check_set = {'A': 0, 'B': 0, 'C': 0, 'D': 0}
        self.__total_cases = 0
        self.__good_cases = 0

    def compute(self):
        self.__compute_check_set()
        self.compute_accuracy()
        self.compute_precision()
        self.compute_rappel()
        self.compute_score()

    def compute_precision(self):
        self.__values["precision"] = self.__good_cases / self.__total_cases

    def __compute_check_set(self):
        total_cases = 0
        good_cases = 0
        check_set = {'A': 0, 'B': 0, 'C': 0, 'D': 0}
        for elem in self.__items:
            total_cases += len(elem)

            copy_elem_checker = []
            for el in elem:
                copy_elem_checker.append(el.get_type())

            for el in copy_elem_checker:
                check_set[el] += 1
            maxim = max(check_set.values())
            good_cases += maxim
        self.__good_cases = good_cases
        self.__total_cases = total_cases
        self.__check_set = check_set

    def compute_accuracy(self):
        self.__values["accuracy"] = sum(self.__check_set.values()) / 2000

    def compute_rappel(self):
        self.__values["rappel"] = sum(self.__check_set.values()) / self.__total_cases

    def compute_score(self):
        self.__values["score"] = (self.__values["rappel"] * self.__values["precision"] * 2) / (
                self.__values["rappel"] + self.__values["precision"])

    def get_values(self):
        return self.__values
