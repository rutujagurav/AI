function accuracy = leave_one_out_cross_validation(data,current_set,features_to_add,choice)
     %     forward selection
    if choice == 1   
        current_set_cols = current_set + 1; % adding 1 to every element of current_set because I need the actual column number in the table
                                            % features_to_add already hold the column number.

        cols_to_send = [current_set_cols features_to_add]; % now the vector cols_to_send has col. nos. of columns of data to be sent to knn

        count_of_correct_classifications = 0;
        total_rows = size(data,1);

         for i = 1 : size(data,1) 

            [nearest_k, indices] = knn(data(:,cols_to_send),i);
            class_labels = data(indices,1);
            class = mode(class_labels);  
            actual_class = data(i,1);

            if(class == actual_class)
                count_of_correct_classifications = count_of_correct_classifications + 1;
            end
         end
          accuracy = (count_of_correct_classifications / total_rows) * 100;
    end

% backward elimination
    if choice == 2
        feature_to_delete = features_to_add;
        set_of_all_cols = [];

        for i = 1 : size(data,2)-1
            set_of_all_cols(i) = i+1;
        end    

        current_set_of_deleted_cols = current_set + 1; % adding 1 to every element of current_set because I need the actual column number in the table
                                                      % features_to_delete already hold the column number.

        cols_not_to_send = [current_set_of_deleted_cols feature_to_delete];

        cols_to_send = setdiff(set_of_all_cols,cols_not_to_send);       

        count_of_correct_classifications = 0;
        total_rows = size(data,1);

          for i = 1 : size(data,1) 

            [nearest_k, indices] = knn(data(:,cols_to_send),i);
            class_labels = data(indices,1);
            class = mode(class_labels);
            actual_class = data(i,1);

            if(class == actual_class)
                count_of_correct_classifications = count_of_correct_classifications + 1;
            end

         end
          accuracy = (count_of_correct_classifications / total_rows) * 100;
    end  

end