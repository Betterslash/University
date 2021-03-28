FILE_NAME = "satellite_constellation_level1.in"
OUT_FILE = "here.out"
N_SCENE = 0
S_RANGE = 0
MAP = {}


def run(r_file_path):
    global S_RANGE, N_SCENE
    fo = open(r_file_path, 'r+')
    N_SCENE = int(fo.readline())
    for i in range(N_SCENE):
        S_RANGE = int(fo.readline())
        clients_num = int(fo.readline())
        for j in range(clients_num):
            line = fo.readline()
            number = line.split(":")[0]
            indexes = line.strip().split(" ")[1:]
            MAP[(int(indexes[0]), int(indexes[1]))] = int(number)
    fo.close()

if __name__ == "__main__":
    run(FILE_NAME)
