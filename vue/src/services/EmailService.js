import emailjs from '@emailjs/browser';

export default {

  sendNewRequestEmail(loggedInEmployee, response, sendEmail) {
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
    console.log("Email will fire now")
    if (1 === 1) { //manual flag used to disable emails during testing.
      return emailjs.send(serviceId, templateId, templateParams, publicKey)
        .then(function (response) {
          // Fire notification that email was sent
          console.log("Email sent!");
        }, function (error) {
          console.error('Email failed to send', error);
        });
    } else {
    // Return a resolved promise when not sending an email
    return Promise.resolve();

      } 
    
    },
    managerSendsApprovalOrReject(loggedInEmployee, response, sendEmail) {
      const email = {
        fromName: loggedInEmployee.employeeName,
        message: `The manager, ${loggedInEmployee.employeeName} has updated the request with the following status: Your request has been ${response.data.approved ? 'Approved' : 'Rejected'}.` + 
                 (response.data.managerMessage ? ` They have included the following message: ${response.data.managerMessage}` : ''),
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
      console.log("Email will fire now")
      if (1 === 0) { //manual flag used to disable emails during testing.
        return emailjs.send(serviceId, templateId, templateParams, publicKey)
          .then(function (response) {
            // Fire notification that email was sent
            console.log("Email sent!");
          }, function (error) {
            console.error('Email failed to send', error);
          });
      } else {
      // Return a resolved promise when not sending an email
      return Promise.resolve();
  
        } 
      
      },
      sendShiftClaimedEmail(loggedInEmployee, response, sendEmail) {
        const email = {
          fromName: loggedInEmployee.employeeName,
          message: `Your shift has been covered by ${loggedInEmployee.employeeName}. Please login and check My Shifts to see the update.`,
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
        console.log("Email will fire now")
        if (1 === 0) { //manual flag used to disable emails during testing.
          return emailjs.send(serviceId, templateId, templateParams, publicKey)
            .then(function (response) {
              // Fire notification that email was sent
              console.log("Email sent!");
            }, function (error) {
              console.error('Email failed to send', error);
            });
        } else {
        // Return a resolved promise when not sending an email
        return Promise.resolve();
    
          } 
        
        }



}