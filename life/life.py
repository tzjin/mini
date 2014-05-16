# Conway's Game of Life
# Built using Numpy and Bokeh, code from tutorial
# http://www.loria.fr/~rougier/teaching/numpy/numpy.html

from  __future__ import division

import numpy as np
import matplotlib.pyplot as plt
from bokeh.plotting import *
from bokeh.objects import * 


# initialize board
def reset():
   return np.array([[0,0,0,0,0,0],
                  [0,0,0,1,0,0],
                  [0,1,0,1,0,0],
                  [0,0,1,1,0,0],
                  [0,0,0,0,0,0],
                  [0,0,0,0,0,0]])


def randomStart(x, y):
   return np.random.randint(0, 2, (x, y))

# one step in game
def iterate(B):
   # count neighbors
   N = (B[0:-2,0:-2] + B[0:-2, 1:-1] + B[0:-2, 2:] + 
        B[1:-1,0:-2]                 + B[1:-1, 2:] + 
        B[2: ,0:-2]  + B[2: , 1:-1]  + B[2: , 2:])

   # determine cell status
   born = (N == 3) & (B[1:-1,1:-1] == 0)              # 3 neighbors, not alive
   live = ((N == 2) | (N == 3)) & (B[1:-1,1:-1] == 1) # 2/3 neighbors, alive
   B[...] = 0                       # reset board
   B[1:-1,1:-1][born | live] = 1    # set living cells
   return B

# display
def disp(B):
   size = np.array(B.shape)
   dpi = 72
   figsize = size[1] / float(dpi), size[0] / float(dpi)
   fig = plt.figure(figsize = figsize, dpi = dpi, facecolor='white')
   fig.add_axes([0.0, 0.0, 1.0, 1.0], frameon = False)
   plt.imshow(B, interpolation='nearest', cmap=plt.cm.gray_r)
   plt.xticks([]), plt.yticks([])
   plt.show()


# run the game
board = randomStart(256, 512)
for i in range(100):
   board = iterate(board)

disp(board)
print board

# next step is plotting with bokeh
