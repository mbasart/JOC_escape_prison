package DAO.util;

public class QueryHelper {
    public static String createQueryINSERT(Object entity) {
        System.out.println("entra a la funcio de INSERT."); //comentaris que poso de prova

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append("iduser");
        for (String field: fields) {
            sb.append(", ").append(field);
        }

        sb.append(") VALUES (?");

        for (String field: fields) {
            sb.append(", ?");
        }

        sb.append(")");

        System.out.println(sb.toString()); //comentaris que poso de prova

        return sb.toString();
    }

    public static String createQuerySELECT(Object entity) {
        System.out.println("entra a la funcio de SELECT."); //comentaris que poso de prova
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }
}
