package persistence.db.mapper;

public interface CustomMapper <In, Out> {
      Out map (In in);
}
