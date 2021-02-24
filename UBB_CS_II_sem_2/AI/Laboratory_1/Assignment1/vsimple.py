

# import the pygame module, so you can use it
import pickle,pygame,sys
from pygame.locals import *
from random import random, randint
import numpy as np
# Python3 program to print DFS traversal
# from a given given graph
from collections import defaultdict

# This class represents a directed graph using
# adjacency list representation

class Vertex:
    def __init__(self, ic, jc):
        self._i = ic
        self._j = jc

    def get_i(self):
        return self._i
    
    def get_j(self):
        return self._j

    def to_string(self):
        return "(" + str(self._i) +", "+str(self._j) +")"

class Edge:
    def __init__(self, fv, sv):
        self._first_vertex = fv
        self._second_vertex = sv
    
    def get_first_vertex(self):
        return self._first_vertex

    def get_second_vertex(self):
        return self._second_vertex


class Graph:

	# Constructor
	def __init__(self):
		# default dictionary to store graph
		self.graph = defaultdict(list)

	# function to add an edge to graph
	def addEdge(self, u, v):
            if v not in self.graph[u]:
	            self.graph[u].append(v)


	# A function used by DFS
	def DFSUtil(self, v, visited, drone):

		# Mark the current node as visited
		# and print it
		visited.add(v)
		print(v, end=' ')

		# Recur for all the vertices
		# adjacent to this vertex
		for neighbour in self.graph[v]:
			if neighbour not in visited:
				self.DFSUtil(neighbour, visited)

	# The function to do DFS traversal. It uses
	# recursive DFSUtil()
	def DFS(self, v, drone):

		# Create a set to store visited vertices
		visited = set()

		# Call the recursive helper function
		# to print DFS traversal
		self.DFSUtil(v, visited, drone)

# Driver code


# Create a graph given
# in the above diagram
g = Graph()




#Creating some colors
BLUE  = (0, 0, 255)
GRAYBLUE = (50,120,120)
RED   = (255, 0, 0)
GREEN = (0, 255, 0)
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)

#define directions
UP = 0
DOWN = 2
LEFT = 1
RIGHT = 3

#define indexes variations
v = [[-1, 0], [1, 0], [0, 1], [0, -1]]


class Environment():
    def __init__(self):
        self.__n = 20
        self.__m = 20
        self.__surface = np.zeros((self.__n, self.__m))
    
    def randomMap(self, fill = 0.2):
        for i in range(self.__n):
            for j in range(self.__m):
                if random() <= fill :
                    self.__surface[i][j] = 1
                
    def __str__(self):
        string=""
        for i in range(self.__n):
            for j in range(self.__m):
                string = string + str(int(self.__surface[i][j]))
            string = string + "\n"
        return string
                
    def readUDMSensors(self, x,y):
        readings=[0,0,0,0]
        # UP 
        xf = x - 1
        while ((xf >= 0) and (self.__surface[xf][y] == 0)):
            xf = xf - 1
            readings[UP] = readings[UP] + 1
        # DOWN
        xf = x + 1
        while (xf < self.__n) and (self.__surface[xf][y] == 0):
            xf = xf + 1
            readings[DOWN] = readings[DOWN] + 1
        # LEFT
        yf = y + 1
        while (yf < self.__m) and (self.__surface[x][yf] == 0):
            yf = yf + 1
            readings[LEFT] = readings[LEFT] + 1
        # RIGHT
        yf = y - 1
        while ((yf >= 0) and (self.__surface[x][yf] == 0)):
            yf = yf - 1
            readings[RIGHT] = readings[RIGHT] + 1
     
        return readings
    
    def saveEnvironment(self, numFile):
        with open(numFile,'wb') as f:
            pickle.dump(self, f)
            f.close()
        
    def loadEnvironment(self, numfile):
        with open(numfile, "rb") as f:
            dummy = pickle.load(f)
            self.__n = dummy.__n
            self.__m = dummy.__m
            self.__surface = dummy.__surface
            f.close()
        
    def image(self, colour = BLUE, background = WHITE):
        imagine = pygame.Surface((420,420))
        brick = pygame.Surface((20,20))
        brick.fill(BLUE)
        imagine.fill(WHITE)
        for i in range(self.__n):
            for j in range(self.__m):
                if (self.__surface[i][j] == 1):
                    imagine.blit(brick, ( j * 20, i * 20))
                
        return imagine        
        
        
class DMap():
    def __init__(self):
        self.__n = 20
        self.__m = 20
        self.surface = np.zeros((self.__n, self.__m))
        for i in range(self.__n):
            for j in range(self.__m):
                self.surface[i][j] = -1
        
        
    def markDetectedWalls(self, e, x, y):
        #   To DO
        # mark on this map the wals that you detect
        wals = e.readUDMSensors(x, y)
        sx = x
        sy = y
        i = x - 1
        if wals[UP] > 0:
            # sx = x
            # sy = y
            while ((i>=0) and (i >= x - wals[UP])):
                g.addEdge((sx, sy), (i, y))
                sx = i
                sy = y
                self.surface[i][y] = 0
                i = i - 1
        if (i>=0):
            self.surface[i][y] = 1
            
        i = x + 1
        if wals[DOWN] > 0:
            # sx = x
            # sy = y
            while ((i < self.__n) and (i <= x + wals[DOWN])):
                g.addEdge((sx, sy), (i, y))
                sx = i
                sy = y
                self.surface[i][y] = 0
                i = i + 1
        if (i < self.__n):
            self.surface[i][y] = 1
            
        j = y + 1
        if wals[LEFT] > 0:
            # sx = x
            # sy = y
            while ((j < self.__m) and (j <= y + wals[LEFT])):
                g.addEdge((sx, sy), (x, j))
                sx = x
                sy = j
                self.surface[x][j] = 0
                j = j + 1
        if (j < self.__m):
            self.surface[x][j] = 1
        
        j = y - 1
        if wals[RIGHT] > 0:
            # sx = x
            # sy = y
            while ((j >= 0) and (j >= y - wals[RIGHT])):
                g.addEdge((sx, sy), (x, j))
                sx = x
                sy = j
                self.surface[x][j] = 0
                j = j - 1
        if (j >= 0):
            self.surface[x][j] = 1
        
        return None
        
    def image(self, x, y):
        
        imagine = pygame.Surface((420,420))
        brick = pygame.Surface((20,20))
        empty = pygame.Surface((20,20))
        empty.fill(WHITE)
        brick.fill(BLACK)
        imagine.fill(GRAYBLUE)
        
        for i in range(self.__n):
            for j in range(self.__m):
                if (self.surface[i][j] == 1):
                    imagine.blit(brick, ( j * 20, i * 20))
                elif (self.surface[i][j] == 0):
                    imagine.blit(empty, ( j * 20, i * 20))
                
        drona = pygame.image.load("D:\\University-Workspace\\UBB_CS_II_sem_2\\AI\\Laboratory_1\\Assignment1\\drona.png")
        imagine.blit(drona, (y *20, x*20))
        return imagine


class Drone():
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def move(self, detectedMap):
        pressed_keys = pygame.key.get_pressed()
        if self.x > 0:
            if pressed_keys[K_UP] and detectedMap.surface[self.x-1][self.y]==0:
                self.x = self.x - 1
        if self.x < 19:
            if pressed_keys[K_DOWN] and detectedMap.surface[self.x+1][self.y]==0:
                self.x = self.x + 1

        if self.y > 0:
              if pressed_keys[K_LEFT]and detectedMap.surface[self.x][self.y-1]==0:
                  self.y = self.y - 1
        if self.y < 19:
              if pressed_keys[K_RIGHT] and detectedMap.surface[self.x][self.y+1]==0:
                  self.y = self.y + 1

    def moveDSF(self, detectedMap):
        pass
         # TO DO!
         #rewrite this function in such a way that you perform an automatic
         # mapping with DFS

# define a main function
tvx = 0
tvy = 0
def main():
    #we create the environment
    e = Environment()
    e.loadEnvironment("D:\\University-Workspace\\UBB_CS_II_sem_2\\AI\\Laboratory_1\\Assignment1\\test2.map")
    #print(str(e))

    # we create the map
    m = DMap()


    # initialize the pygame module
    pygame.init()
    # load and set the logo
    logo = pygame.image.load("D:\\University-Workspace\\UBB_CS_II_sem_2\\AI\\Laboratory_1\\Assignment1\\logo32x32.png")
    pygame.display.set_icon(logo)
    pygame.display.set_caption("drone exploration")



    # we position the drone somewhere in the area
    x = randint(0, 19)
    y = randint(0, 19)
    tvx = x
    tvy = y
    #cream drona
    d = Drone(x, y)



    # create a surface on screen that has the size of 800 x 480
    screen = pygame.display.set_mode((800,400))
    screen.fill(WHITE)
    screen.blit(e.image(), (0,0))

    # define a variable to control the main loop
    running = True

    # main loop
    while running:
        # event handling, gets all event from the event queue
        for event in pygame.event.get():
            # only do something if the event is of type QUIT
            if event.type == pygame.QUIT:
                # change the value to False, to exit the main loop
                running = False
            if event.type == KEYDOWN:
                # use this function instead of move
                #d.moveDSF(m)
                d.move(m)
        m.markDetectedWalls(e, d.x, d.y)
        screen.blit(m.image(d.x,d.y),(400,0))
        pygame.display.flip()
    print(g.graph)
    pygame.quit()


# run the main function only if this module is executed as the main script
# (if you import this as a module then nothing is executed)
if __name__=="__main__":
    # call the main function
    main()
    