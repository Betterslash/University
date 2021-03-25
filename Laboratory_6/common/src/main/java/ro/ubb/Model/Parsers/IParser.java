package ro.ubb.Model.Parsers;

import ro.ubb.Model.BaseEntity;

public interface IParser<ID, E extends BaseEntity<ID>> {
    E parse(String body);
    ID parseID(String body);
}
