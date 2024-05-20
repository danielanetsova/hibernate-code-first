package entities;

import javax.persistence.*;

//Използваме TABLE_PER_CLASS защото не искаме този конкретен клас да се показва в базата а само неговите наследници
// тоест в базата няма да има billing_details таблица, но ще има 2-те таблици credit_card и bank_account, като във всяка
//една от тях ще се показват полетата принадлежащи на класа BillingDetails. Id-to вече е генерирано в BillingDetails
//=> на BankAccount и на CreditCard id не се добавя
@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetails {
    @Id
    private String uuid; //преподавателят сложи тук String но аз си предпочитам така :)

    @ManyToOne
    private User owner;
}
