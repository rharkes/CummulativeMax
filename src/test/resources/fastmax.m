%% load data
file = 'PC_PI_Col0_1.tif';
info = imfinfo(file);
N = length(info);
A = imread(file, 1, 'Info', info);
num_images = numel(info);
IM = zeros([size(A),num_images],class(A));
IM(:,:,1)=A;
for k = 2:num_images
    A(:,:,k) = imread(file, k, 'Info', info);
end

%% maximum
tic
M = cummax(A,3);
toc % 57 milliseconds

%% display
for ct = 1:size(M,3)
    imagesc(M(:,:,ct))
    pause(0.1);
end