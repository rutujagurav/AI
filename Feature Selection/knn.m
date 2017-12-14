function [nearest_k, inds_of_nearest_k] = knn(data,data_elem_row)
    N = size(data,1);
    k = 3;

    data_elem = data(data_elem_row,:);
    distance = zeros(1,N);
    for i = 1 : N
        if i ~= data_elem_row
            distance(i) = sqrt(sum((data(i,:) - data_elem).^2));
        end   
    end
    % sorting distances
    [sorted_distances, ind] = sort(distance);
    
    % getting k nearest neighbours and their indexes in data table
    inds_of_nearest_k = ind(1:k);
    nearest_k = data(inds_of_nearest_k, :); 

end