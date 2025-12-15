┌─────────────────────────────────────────────────────────────────┐
│                         DOMAIN MODEL                             │
└─────────────────────────────────────────────────────────────────┘

┌──────────────────┐         ┌──────────────────┐
│     Client       │         │      Buyer       │
├──────────────────┤         ├──────────────────┤
│ - id: Long       │         │ - id: Long       │
│ - name: String   │         │ - email: String  │
│ - createdAt      │         │ - cpf: String    │
│ - updatedAt      │         │ - nome: String   │
└────────┬─────────┘         └─────────┬────────┘
│                             │
│ 1                           │ 1
│                             │
│    * ┌──────────────┐ *     │
└──────│   Payment    │───────┘
├──────────────┤
│ - id: Long   │
│ - amount     │◄────────┐
│ - status     │         │
│ - type       │         │ 1
│ - createdAt  │         │
│ - updatedAt  │    ┌────┴─────────────┐
└──────┬───────┘    │ PaymentStatus    │
│            │ (ENUM)           │
│ 1          ├──────────────────┤
│            │ CREATED          │
┌──────┴────────┐   │ IN_ANALYSIS      │
│               │   │ AUTHORIZED       │
│ PaymentType   │   │ CANCELLED        │
│   (ENUM)      │   │ SETTLED          │
│               │   │ FAILED           │
└───────────────┘   └──────────────────┘
△
┌─────────────┼─────────────┬──────────────┐
│             │             │              │
┌────────┴────────┐ ┌──┴──────────┐ ┌┴─────────────┐ ┌┴──────────────┐
│ BoletoPayment   │ │ CardPayment │ │ PixPayment   │ │ CashPayment   │
├─────────────────┤ ├─────────────┤ ├──────────────┤ ├───────────────┤
│ - paymentId: FK │ │ - paymentId │ │ - paymentId  │ │ - paymentId   │
│ - barcodeNumber │ │ - cardId: FK│ │ - pixKey     │ │ - receiptNumber│
│ - digitableLine │ │ - lastDigits│ │ - qrCode     │ │ - confirmedAt │
│ - dueDate       │ │ - brand     │ │ - txid       │ │ - confirmedBy │
│ - paidAt        │ │ - installm. │ │ - e2eId      │ └───────────────┘
└─────────────────┘ └──────┬──────┘ └──────────────┘
│ 1
│
┌──────┴──────┐
│ CreditCard  │
├─────────────┤
│ - id: Long  │
│ - token     │◄─────┐
│ - brand     │      │ 1
│ - lastDigits│      │
│ - expiryMM  │ ┌────┴────────┐
│ - expiryYY  │ │   Holder    │
│ - holderId  │ ├─────────────┤
│ - isActive  │ │ - id: Long  │
└─────────────┘ │ - name      │
│ - birthDate │
│ - document  │
└─────────────┘