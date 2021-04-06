class CommandReader:
    @staticmethod
    def read_command():
        commannds = input("Give here the initialization parameters >> ")
        commannds = commannds.strip()
        commannds = commannds.split(" ")
        return commannds
