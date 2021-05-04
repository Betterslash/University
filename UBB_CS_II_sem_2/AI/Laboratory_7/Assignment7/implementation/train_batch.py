import math

import torch

from Assignment7.implementation import mymodel

xT = (-20. * torch.rand(1000) + 10.)
yT = (-20. * torch.rand(1000) + 10.)
DATA = torch.column_stack((xT.clone(), yT.clone()))
torch.save(DATA, 'database/mydataset.dat')
# # the function to be optimised
x = torch.load('database/mydataset.dat')
y = torch.sin(xT.clone() + yT.clone() / math.pi).clone()
# we set up the lossFunction as the mean square error
lossFunction = torch.nn.L1Loss()

# we create the ANN
ann = mymodel.Net(n_feature=2, n_hidden=10, n_output=1)

print(ann)
# we use an optimizer that implements stochastic gradient descent
optimizer_batch = torch.optim.SGD(ann.parameters(), lr=0.2)

# we memorize the losses forsome graphics
loss_list = []
avg_loss_list = []

# we set up the environment for training in batches
batch_size = 32
n_batches = int(len(x) / batch_size)
print(n_batches)

for epoch in range(2000):

    for batch in range(n_batches):
        batch_X, batch_y = x[batch * batch_size:(batch + 1) * batch_size, ], y[batch * batch_size:(batch + 1) * batch_size, ]
        # we compute the output for this batch
        prediction = ann(torch.FloatTensor(batch_X))
        batch_y = torch.FloatTensor(batch_y)
        # we compute the loss for this batch
        loss = lossFunction(prediction, batch_y)

        # we save it for graphics
        loss_list.append(loss)

        # we set up the gradients for the weights to zero (important in pytorch)
        optimizer_batch.zero_grad()

        # we compute automatically the variation for each weight (and bias) of the network
        loss.backward()

        # we compute the new values for the weights
        optimizer_batch.step()

        # we print the loss for all the dataset for each 10th epoch
    if epoch % 100 == 99:
        y_pred = loss_list[len(loss_list) - 1]
        loss = lossFunction(y_pred, y)
        print('\repoch: {}\tLoss =  {:.5f}'.format(epoch, loss))

    # Specify a path
filepath = "database/mydataset.dat"

# save the model to file
torch.save(ann.state_dict(), filepath)
if __name__ == "__main__":
    pass
