import torch

from Assignment7.implementation import mymodel
from Assignment7.implementation.constants import Constants
from Assignment7.implementation.data_loader import DataLoader


class Solver:

    @staticmethod
    def execute():

        # create data for the ann
        DataLoader().load_data()

        # load the data in memory
        x_points, y_points, f_tensor = torch.load(Constants.DATA_PATH)

        # merging x_points with y_points to create a tensor of vars of point form
        variabls = torch.column_stack((x_points, y_points))

        f_tensor = f_tensor.unsqueeze(1)

        loss_function = torch.nn.MSELoss()

        ann = mymodel.Net(n_feature=2, n_hidden=10, n_output=1)

        print(ann)

        optimizer_batch = torch.optim.SGD(ann.parameters(), lr=0.02)

        loss_list = []
        avg_loss_list = []

        batch_size = 16
        n_batches = int(len(f_tensor) / batch_size)
        print(n_batches)

        for epoch in range(2000):

            loss_sum = 0

            for batch in range(n_batches):
                batch_variables = variabls[batch * batch_size:(batch + 1) * batch_size, ]
                batch_function = f_tensor[batch * batch_size:(batch + 1) * batch_size, ]

                prediction = ann(batch_variables)

                loss = loss_function(prediction, batch_function)
                loss_sum += loss.item()
                optimizer_batch.zero_grad()
                loss.backward()
                optimizer_batch.step()

            avg_loss_list.append(loss_sum / n_batches)
            f_pred = ann(variabls)
            loss = loss_function(f_pred, f_tensor)
            loss_list.append(loss.item())
            if epoch % 100 == 99:
                print('\rEpoch: {}\tLoss =  {:.5f}'.format(epoch, loss))

        torch.save(ann.state_dict(), Constants.DICT_STATES_PATH)

