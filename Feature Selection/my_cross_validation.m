function accuracy = my_cross_validation(data,current_set,features_to_add,best_so_far_accuracy)
 % smart forward selection
            
        threshold = size(data,1) - best_so_far_accuracy;
           
        current_set_cols = current_set + 1; % adding 1 to every element of current_set because I need the actual column number in the table
                                            % features_to_add already hold the column number.

        cols_to_send = [current_set_cols features_to_add]; % now the vector cols_to_send has col. nos. of columns of data to be sent to knn

        count_of_wrong_classifications = 0;
        total_rows = size(data,1);
        flag = 0;

         for i = 1 : size(data,1) 

            [nearest_k, indices] = knn(data(:,cols_to_send),i);
            class_labels = data(indices,1);
            class = mode(class_labels);  
            actual_class = data(i,1);

              if(class ~= actual_class)
                 count_of_wrong_classifications = count_of_wrong_classifications + 1;
                 if(count_of_wrong_classifications > threshold)
                     flag = 1;
                     break;
                 end
              end        
         end
        
         if(flag == 1)
             accuracy = 0;
         else
             count_of_correct_classifications = size(data,1) - count_of_wrong_classifications;
             accuracy = (count_of_correct_classifications / total_rows) * 100;
         end
end