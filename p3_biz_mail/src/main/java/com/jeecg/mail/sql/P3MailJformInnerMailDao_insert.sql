INSERT  INTO
	jp_inner_mail
      ( 
      id                            
      ,create_name                    
      ,create_by                      
      ,create_date                    
      ,title                          
      ,attachment                     
      ,content                        
      ,status                         
      ,receiver_names                 
      ,receiver_ids                   
      ) 
values
      (
      :p3MailJformInnerMail.id
      ,:p3MailJformInnerMail.createName
      ,:p3MailJformInnerMail.createBy
      ,:p3MailJformInnerMail.createDate
      ,:p3MailJformInnerMail.title
      ,:p3MailJformInnerMail.attachment
      ,:p3MailJformInnerMail.content
      ,:p3MailJformInnerMail.status
      ,:p3MailJformInnerMail.receiverNames
      ,:p3MailJformInnerMail.receiverIds
      )