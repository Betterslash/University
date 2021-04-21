from random import randint, random


class ACOConstants:
    NOANTS = 700
    ALPHA = 1.9
    BETA = 0.9
    RHO = 0.01
    Q0 = 0.5
    SENSORS = [(4, 2), (3, 11), (8, 16), (11, 2), (18, 4), (7, 19), (18, 14), (10, 10)]

    @staticmethod
    def get_accesible(current_pos, t_map, energy):
        accesible = set()
        if energy > 0:
            x_coord = current_pos[0]
            dummy_e = energy
            while dummy_e > 0 and x_coord >= 0 and t_map[x_coord][current_pos[1]] != 1:
                accesible.add((x_coord, current_pos[1]))
                x_coord -= 1
                dummy_e -= 1

            y_coord = current_pos[1]
            dummy_e = energy
            while dummy_e > 0 and y_coord <= 19 and t_map[current_pos[0]][y_coord] != 1:
                accesible.add((current_pos[0], y_coord))
                y_coord += 1
                dummy_e -= 1

            x_coord = current_pos[0]
            dummy_e = energy
            while dummy_e > 0 and x_coord <= 19 and t_map[x_coord][current_pos[1]] != 1:
                accesible.add((x_coord, current_pos[1]))
                x_coord += 1
                dummy_e -= 1

            y_coord = current_pos[1]
            dummy_e = energy
            while dummy_e > 0 and y_coord >= 0 and t_map[current_pos[0]][y_coord] != 1:
                accesible.add((current_pos[0], y_coord))
                y_coord -= 1
                dummy_e -= 1
        return len(accesible)

    @staticmethod
    def selection(next_n_prob):
        # Here I chose a roulette selection because i don't want a rapind converging
        probability_sum = sum(next_n_prob)
        if probability_sum == 0:
            return randint(0, len(next_n_prob) - 1)
        partipartial_sums = [next_n_prob[0] / probability_sum]
        for i in range(1, len(next_n_prob)):
            partipartial_sums.append(partipartial_sums[i - 1] + next_n_prob[i] / probability_sum)
        r = random()
        position = 0
        while r > partipartial_sums[position]:
            position += 1
        return position
