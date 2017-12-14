filedata = importdata('survival.txt');
class = filedata(:,4);
survivalData = filedata(:,[1 2 3]);
data = [class survivalData];

choice = 1;

norm_data  = data;
disp('Normalizing data...')
for col = 2 : size(data,2)-1
%     norm_data(:,col) = (data(:,col) - min(data(:,col))) / (max(data(:,col)) - min(data(:,col)));
    norm_data(:,col) = (data(:,col) - mean(data(:,col))) / std(data(:,col));
end

if choice == 1 %forward selection
%     now1 = tic();
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
%     functionTime_1 = toc(now1);
%     disp(['Time in seconds: ',num2str(functionTime_1)])
    
end

