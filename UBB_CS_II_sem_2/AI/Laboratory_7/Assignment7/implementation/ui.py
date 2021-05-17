import torch

import mymodel
# we load the model
from Assignment7.implementation.constants import Constants
from Assignment7.implementation.train_batch import Solver

if __name__ == "__main__":

    Solver.execute()

    ann = mymodel.Net(2, 16, 1)

    ann.load_state_dict(torch.load(Constants.DICT_STATES_PATH))
    ann.eval()

    # visualise the parameters for the ann (aka weights and biases)
    # for name, param in ann.named_parameters():
    #     if param.requires_grad:
    #         print(name, param.data)

    x = float(input("x = "))
    y = float(input("y = "))
    x = torch.tensor([x, y])
    print(ann(x).tolist())
