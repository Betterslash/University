import torch

import mymodel

# we load the model
if __name__ == "__main__":
    filepath = "database/mydataset.dat"
    ann = mymodel.Net(2, 10, 1)

    ann.load_state_dict(torch.load(filepath))
    ann.eval()

    # visualise the parameters for the ann (aka weights and biases)
    for name, param in ann.named_parameters():
        if param.requires_grad:
            print(name, param.data)

    x = float(input("x = "))
    y = float(input("y = "))
    x = torch.tensor([x, y])
    print(ann(x).tolist())
