% My datasets - small 59 large 64
% large - 25 42 16
% small - 6 7 4

disp('Welcome to Rutuja Guravs Feature Selection Algorithm')
msg = 'Type in the name of the file that you would like to test:';
filename = input(msg); 
data = importdata(filename);
% data = importdata('small.txt');
% choice = 1; 

[rows,columns] = size(data);

message = 'Enter a choice of Search Algorithm:';
disp('1. Forward Selection')
disp('2. Backward Elimination')
disp('3. My modified Forward Selection')

choice = input(message);

norm_data  = data;
disp('Normalizing data...')
for col = 2 : size(data,2)-1
%     norm_data(:,col) = (data(:,col) - min(data(:,col))) / (max(data(:,col)) - min(data(:,col)));
    norm_data(:,col) = (data(:,col) - mean(data(:,col))) / std(data(:,col));
end


if choice == 1 %forward selection
    now1 = tic();
    current_set_of_features = []; % Initialize an empty set
    set_at_each_level = [];
    levels = size(data,2)-1; 
 
    for i = 1 : size(data,2)-1 
%     disp(['On the ',num2str(i),'th level of the search tree'])
        feature_to_add_at_this_level = [];
        best_so_far_accuracy    = 0;
    
        for k = 1 : size(data,2)-1      
            if isempty(intersect(current_set_of_features,k)) % Only consider adding, if not already added.
    %             disp(['--Considering adding the ', num2str(k),' feature'])
                accuracy = leave_one_out_cross_validation(norm_data,current_set_of_features,k+1,choice); 
                %sent the whole data, an array with best-so-far features + to store new best features, and (k+1)th feature, i.e. 1st feature (k+1 as the 1st col is not a feature) 
                if accuracy > best_so_far_accuracy
                    best_so_far_accuracy = accuracy;
                    feature_to_add_at_this_level = k; 
                end
            end  
        end
 
        current_set_of_features(i) =  feature_to_add_at_this_level;
        disp(['On level ', num2str(i),' I added feature ', num2str(feature_to_add_at_this_level), ' to current set'])
        disp(['Using features {', num2str(current_set_of_features),'}, accuracy is =', num2str(best_so_far_accuracy),'%'])    
    end
    functionTime_1 = toc(now1);
    disp(['Time in seconds: ',num2str(functionTime_1)])
    
end



if choice == 2 % backward elimination
    now2 = tic();
    set_of_all_features = [];
        for i = 1 : size(data,2)-1
            set_of_all_features(i) = i;
        end 
    deleted_features = [];

    for i = 1:size(data,2)-1 
%         disp(['On the ',num2str(i),'th level of the search tree'])
        feature_to_delete_at_this_level = 0;
        best_so_far_accuracy    = 0;

        for k = 1 : size(data,2)-1
            if isempty(intersect(deleted_features,k)) % Only consider deleting, if not already deleted.
    %            disp(['--Considering deleting the ', num2str(k),' feature'])
                accuracy = leave_one_out_cross_validation(norm_data, deleted_features, k+1,choice); 
               if accuracy > best_so_far_accuracy
                    best_so_far_accuracy = accuracy; % best accuracy given by using features other than all the previously deleted features and kth feature
                    feature_to_delete_at_this_level = k;
               end
            end 
        end
 
        deleted_features(i) = feature_to_delete_at_this_level;
        disp(['On level ', num2str(i),' I deleted feature ', num2str(feature_to_delete_at_this_level), ' from current set'])
%         disp(['Deleting features {', num2str(deleted_features),'}'])
        features_used = setdiff(set_of_all_features,deleted_features);
        disp(['Using features {', num2str(features_used),'}, accuracy is =', num2str(best_so_far_accuracy),'%'])
    end 
    functionTime_2 = toc(now2);
    disp(['Time in seconds: ',num2str(functionTime_2)])
    
end

%-----------------------------------------------------------------------------------------------------------------------

if choice == 3 %smart forward selection
    now3 =tic();
    current_set_of_features = []; % Initialize an empty set
    set_at_each_level = [];
    levels = size(data,2)-1; 
 
    for i = 1 : size(data,2)-1 
%     disp(['On the ',num2str(i),'th level of the search tree'])
        feature_to_add_at_this_level = [];
        best_so_far_accuracy    = 0;
    
        for k = 1 : size(data,2)-1      
            if isempty(intersect(current_set_of_features,k)) % Only consider adding, if not already added.
    %             disp(['--Considering adding the ', num2str(k),' feature'])
                accuracy = my_cross_validation(norm_data,current_set_of_features,k+1,best_so_far_accuracy);
                disp(['Accuracy returned by my modified search for {',num2str([current_set_of_features k]),'} is: ',num2str(accuracy)])
%                 disp(accuracy)
                %sent the whole data, an array with best-so-far features + to store new best features, and (k+1)th feature, i.e. 1st feature (k+1 as the 1st col is not a feature) 
                if accuracy > best_so_far_accuracy
                    best_so_far_accuracy = accuracy;
                    feature_to_add_at_this_level = k;
                end
            end  
        end

        current_set_of_features(i) =  feature_to_add_at_this_level;
        disp(['On level ', num2str(i),' I added feature ', num2str(feature_to_add_at_this_level), ' to current set'])
        disp(['Using features {', num2str(current_set_of_features),'}, accuracy is =', num2str(best_so_far_accuracy),'%'])    
    end
    functionTime_3 = toc(now3);
    disp(['Time in seconds: ',num2str(functionTime_3)])
end

%-----------------------------------------------------------------------------------------------------------------------
% plotting
    
%     X1 = norm_data(:,7);
%     X2 = norm_data(:,8);
%     X3 = norm_data(:,5);
%     Y = zeros(size(X1));
% 
%     scatter(X1,Y,'r','*'); 
%     hold all
%     scatter(X2,Y,'b');
%     hold all
%     scatter(X3,Y,'g','+');
% %     title('Selected features for small dataset');
%     
%     
%     Z = norm_data(:,1);
%     
%     scatter(X1,Z,'r','*');
%     hold all
%     scatter(X2,Z,'b');
%     hold all
%     scatter(X3,Z,'g','+');

%     X1 = norm_data(:,17);
%     X2 = norm_data(:,43);
%     X3 = norm_data(:,26);
%     Y = zeros(size(X1));
% 
%     scatter(X1,Y,'r','*'); 
%     hold all
%     scatter(X2,Y,'b');
%     hold all
%     scatter(X3,Y,'g','+');
%     
%     Z = norm_data(:,1);
%     
%     scatter(X1,Z,'r','*');
%     hold all
%     scatter(X2,Z,'b');
%     hold all
%     scatter(X3,Z,'g','+');


