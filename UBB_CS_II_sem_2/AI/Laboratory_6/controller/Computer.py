from model.ItemEntry import ItemEntry


class Computer:
    def __init__(self, clusters):
        self.__items = clusters
        self.__values = {"accuracy": 0., "precision": 0, "rappel": 0, "score": 0}
        self.__check_set = {'A': 0, 'B': 0, 'C': 0, 'D': 0}
        self.__total_cases = 0
        self.__true_positive = 0
        self.__false_positive = 0
        self.__false_negative = 0
        self.__true_negative = 0
        self.__cluster_stats = {'A': {"accuracy": 0., "precision": 0, "rappel": 0, "score": 0},
                                'B': {"accuracy": 0., "precision": 0, "rappel": 0, "score": 0},
                                'C': {"accuracy": 0., "precision": 0, "rappel": 0, "score": 0},
                                'D': {"accuracy": 0., "precision": 0, "rappel": 0, "score": 0}}

    def compute(self, items):
        self.__compute_check_set(items)

    def __compute_check_set(self, all_points: list[ItemEntry]):
        total_cases = 0
        good_cases = 0
        for elem in self.__items:
            total_cases += len(elem)
            check_set = {'A': 0, 'B': 0, 'C': 0, 'D': 0}
            copy_elem_checker = []
            for el in elem:
                copy_elem_checker.append(el.get_type())

            for el in copy_elem_checker:
                check_set[el] += 1
            maxim = max(check_set.values())
            good_cases += maxim
            true_positives = maxim
            false_positive = sum(check_set.values()) - maxim
            true_negatives = 0
            false_negatives = 0
            type_value = max(check_set, key=check_set.get)
            for point in all_points:
                if point not in elem:
                    if point.get_type() != type_value:
                        true_negatives += 1
                    else:
                        false_negatives += 1
            accuracy = (true_positives + true_negatives) / (
                    true_positives + true_negatives + false_positive + false_negatives)
            if true_positives == 0:
                precision = 0
            else:
                precision = true_positives / (true_positives + false_positive)
            rappel = true_positives / (true_positives + false_negatives)
            if precision == 0:
                score = 0
            else:
                score = 2 * precision * rappel / (precision + rappel)
            self.__cluster_stats[type_value]["accuracy"] = accuracy
            self.__cluster_stats[type_value]["precision"] = precision
            self.__cluster_stats[type_value]["rappel"] = rappel
            self.__cluster_stats[type_value]["score"] = score

    def get_values(self):
        return self.__values

    def get_stats(self):
        return self.__cluster_stats
