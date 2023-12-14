export default {

    sendNewRequestEmail(loggedInEmployee, response){
        const email = {
            fromName: loggedInEmployee.employeeName,
            message: `The employee, ${loggedInEmployee.employeeName} has requested time off for the day of ${response.data.date}. They have included the following message: ${response.data.employeeMessage}` + (response.data.emergency ? " This is an emergency request!" : ""),
            replyTo: loggedInEmployee.email
          }
    
          const templateParams = {
            from_name: email.fromName,
            message: email.message,
            reply_to: email.replyTo
          };
    
          const publicKey = 'VZnKmVeJRMukHAUH0';
          const templateId = 'template_r7geovx';
          const serviceId = 'service_xsowi2y';

          return emailjs.send(serviceId, templateId, templateParams,publicKey)
            .then(function (response) {
              // Fire notification that email was sent
              console.log("Email sent!");
            }, function (error) {
              console.error('Email failed to send', error);
            });
    
            
    }


    
}