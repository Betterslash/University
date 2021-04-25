from random import choice

from matplotlib import pyplot;

from test_2 import ReadData, CalculateMeans, FindClusters


def CutToTwoFeatures(items, indexA, indexB):
    n = len(items);
    X = [];
    for i in range(n):
        item = items[i];
        newItem = [item[indexA], item[indexB]];
        X.append(newItem);

    return X;


def PlotClusters(clusters):
    n = len(clusters);
    # Cut down the items to two dimension and store to X
    X = [[] for _ in range(n)];

    for i in range(n):
        cluster = clusters[i];
        for item in cluster:
            X[i].append(item);

    colors = ['r', 'b', 'g', 'c', 'm', 'y'];

    for x in X:
        # Choose color randomly from list, then remove it
        # (to avoid duplicates)
        c = choice(colors);
        colors.remove(c);

        Xa = [];
        Xb = [];

        for item in x:
            Xa.append(item[0]);
            Xb.append(item[1]);

        pyplot.plot(Xa, Xb, 'o', color=c);

    pyplot.show();


def main():
    items = ReadData('dataset.csv')

    k = 4;
    means = CalculateMeans(k, items);
    clusters = FindClusters(means, items);

    PlotClusters(clusters);

if __name__ == "__main__":
    main()