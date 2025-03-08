# Safe-Policy

Safe Policy is a policy-based project featuring a dynamic form journey for calculating policy premiums. The premium is determined based on factors such as annual income, premium paying term, policy term, and payment frequency. Currently, the project includes one product—Term Insurance, but it is designed to support the addition of multiple products in the future.

### Technologies Used:
- Java  
- Spring Boot  
- Spring Security  
- JPA (Java Persistence API)  
- Thymeleaf  
- PayPal Payment Integration  
- Bootstrap v5.2.2  
- HTML, CSS, JavaScript  
- Pagination  
- PDF Generation
  
https://github.com/user-attachments/assets/453a7b7a-d7f0-4ccd-b187-9c2d65cd8a9b

In this video, we showcase the admin journey.

![RegisterPageImage](https://github.com/user-attachments/assets/1e9c249c-e163-477e-bec1-b7951dc0f8ec)

This is the section where new users can sign up.

<p align="center">
  <img src="https://github.com/user-attachments/assets/a00b66f0-eabd-493b-98e8-23581bfb2cbd" alt="PersonalDetailsImage">
</p>

On the Personal Details page, users can view all their registered information. Additionally, the annual income field is dynamically configured, determining the sum assured accordingly.

<p align="center">
  <img src="https://github.com/user-attachments/assets/2a17912a-43df-42a9-8651-a697159dda91" alt="IncomeDetailImage">
</p>

The Income Details page is where the premium is calculated. The sum assured is determined based on the annual income selected on the previous page. The premium is then calculated using the premium paying term, policy term, and payment frequency. Additionally, a product discount can be dynamically applied, allowing us to determine the final total premium.

<p align="center">
  <img src="https://github.com/user-attachments/assets/5b58be45-c2f7-4a5e-9854-b3c4f1f4fcf8" alt="AddOnImage">
</p>

To enhance your term insurance, we offer add-on benefits that can be included in your premium.

<p align="center">
  <img src="https://github.com/user-attachments/assets/d2e27058-44ab-497f-afef-ecad44725e89" alt="PaymentImage">
</p>

The payment page is the final step of our form journey, where you can view the final premium amount and proceed with the payment.

![HeaderImage](https://github.com/user-attachments/assets/da1fc6cb-64c5-451e-9d1e-673b650d48a5)

Throughout this journey, we have an additional identifier called the Quote ID. Additionally, there are two pages in the header—Product Page and Configuration Page—that are accessible only to the admin.

![PolicyDetailImage](https://github.com/user-attachments/assets/d2f04b33-3ed9-4020-b6c1-3570d3057f76)

After completing the payment, the user is redirected to the Policy Details page, where all generated policies are available and can also be downloaded. We have also implemented pagination for easy navigation. On this page, users can view policy details such as status, final premium, application number, and more.

![PolicyDetailImage2](https://github.com/user-attachments/assets/62857462-fc3e-4639-b8aa-5ed32adccb5e)


![ProductImage](https://github.com/user-attachments/assets/0470eae5-e19b-4fe2-9ed5-976322fca5e5)

Here, you can see the Product page, where you can dynamically add discounts to a specific product. These discounts will be reflected on the Income Details page.

![ConfigPageImage](https://github.com/user-attachments/assets/00c38cc5-9872-4cc5-8974-f4ce7e181eaf)

The Configuration page allows you to set the annual income, based on which the sum assured is determined dynamically. Additionally, you can configure add-on benefits dynamically as well.

![PdfImage](https://github.com/user-attachments/assets/0a99d33e-4729-4e63-b914-d8c86e34cef4)

Here's the PDF of the Generated Policy.
