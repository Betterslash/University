import math

import torch

from Assignment7.implementation.constants import Constants


class DataLoader:

    @staticmethod
    def load_data():
        x_vars = torch.rand(1000) * 20 - 10
        y_vars = torch.rand(1000) * 20 - 10
        f_tensor = torch.sin(torch.add(x_vars, y_vars, alpha=1 / math.pi))
        pairs = torch.stack((x_vars, y_vars, f_tensor))
        torch.save(pairs, Constants.DATA_PATH)
