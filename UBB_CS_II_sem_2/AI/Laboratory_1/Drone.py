class Drone:
    def __init__(self):
        self._firstIteration = True

    def readUDMSensors(self):
        if self._firstIteration :
            self._firstIteration = False
            return [(1,3), (5,1), (5,4), (7,3)]

    def toString(self):
        return self.readUDMSensors()
        
drone = Drone()
print(drone.toString())