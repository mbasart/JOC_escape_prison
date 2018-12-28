package DAO.util;

public class QueryHelper {
    public static String createQueryINSERT(Object entity) {
        System.out.println("entra a la funcio de INSERT."); //comentaris que poso de prova

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append("id");
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
        sb.append(" WHERE id = ?");

        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity){
        System.out.println("Entra a la funcio de UPDATE.");
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(entity.getClass().getSimpleName()).append(" ").append("SET");

        String [] fields = ObjectHelper.getFields(entity);

        for(String field: fields){
            sb.append(" ").append(field);
            sb.append(" = ?,");
        }
        sb.delete(sb.length() -1, sb.length());

        sb.append(" WHERE id = ?");

        return sb.toString();
    }

}
